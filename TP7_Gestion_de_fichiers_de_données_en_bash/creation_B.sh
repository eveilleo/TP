groupadd t_users
while IFS=";" read v_nom v_passwd 
do
    echo "nom=$v_nom"
    v_passwd=$(pwgen --num-passwords=1)
    adduser --disabled-password --gecos "" --ingroup t_users "$v_nom"
    echo $v_nom:$v_passwd | chpasswd
    echo "nom=$v_nom passwd=$v_passwd"
done < liste3.txt
