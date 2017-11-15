# encoding: utf-8

from swiglpk import *
def solve(json):
    ia = intArray(1+1000);
    ja = intArray(1+1000);
    ar = doubleArray(1+1000);

    lp = glp_create_prob();
    glp_set_prob_name(lp,"Simplex");

    if(json['maxmin'] == 'MAX'):
        glp_set_obj_dir(lp, GLP_MAX);
        print("MAX!")
    else:
        glp_set_obj_dir(lp, GLP_MIN);
        print("MIN!")

    num_vars = len(json['Z'])
    print("NUM VARS = " + str(num_vars));
    glp_add_cols(lp,num_vars);

    for i in range(1,num_vars+1):
        glp_set_col_name(lp,i,"x"+str(i));
        glp_set_col_bnds(lp,i,GLP_LO,0.0,0.0);
        glp_set_col_kind(lp,i,GLP_IV);
        print(json['Z'][i-1]);
        glp_set_obj_coef(lp,i,json['Z'][i-1]);


    num_rests = json['rests']
    glp_add_rows(lp,num_rests);
    print("NUM RESTS = " + str(num_rests))

    for i in range(1,num_rests+1):
        glp_set_row_name(lp,i,"r"+str(i));
        rest = json['R'+str(i-1)]
        #print(rest[num_vars])
        if(rest[num_vars] == '<='):
            #print(rest[num_vars+1])
            glp_set_row_bnds(lp,i,GLP_UP,0.0,rest[num_vars+1]);
        elif (rest[num_vars] == '>='):
            #print(rest[num_vars+1])
            glp_set_row_bnds(lp,i,GLP_LO,rest[num_vars+1],0.0);
    counter = 1
    for i in range(1,num_rests+1):
        for j in range (1,num_vars+1):
            ia[counter] = i;
            ja[counter] = j;
            ar[counter] = json["R"+str(i-1)][j-1];
            counter = counter + 1
    glp_load_matrix(lp,num_rests * num_vars,ia,ja,ar);
    glp_simplex(lp,None);
    pls = glp_iocp();
    glp_init_iocp(pls);
    glp_intopt(lp,pls);
    status = glp_get_status(lp);
    statusInt = glp_mip_status(lp);
    print("status = " + str(status) + "OPT = " + str(GLP_OPT))
    print("statusInt = " + str(statusInt))
    nomes = ['Bolas de Sorvete','Picolés Cremosos','Picolés de Fruta']
    resp = "Erro Inesperado\n"
    if (status == GLP_OPT and (statusInt == GLP_OPT or statusInt == GLP_FEAS ) ):
        resp = "Solucao Otima encontrada!\n"
        Z = glp_get_obj_val(lp)
        resp += "Lucro Ótimo Encontrado: "  + str(Z) + "\n"
        resp += "Para produzir o lucro máximo são necessários:\n"
        for i in range(1,num_vars+1):
            x = glp_mip_col_val(lp,i);
            resp += str(x) + " " + nomes[i-1] + "\n"
    elif(status == GLP_INFEAS or status == GLP_UNDEF or statusInt == GLP_NOFEAS  or statusInt == GLP_UNDEF):
        resp = "Solucao Impossivel!\n"
    elif(status == GLP_UNBND ):
        resp = "Solucao Ilimitada!\n"
    glp_delete_prob(lp);
    return (resp)
