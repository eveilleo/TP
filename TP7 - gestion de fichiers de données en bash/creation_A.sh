groupadd t_users

while IFS= read -r username; do
    adduser --disabled-password --gecos "" --ingroup t_users "$username"
    echo "L'utilisateur $username a été créé et ajouté au groupe t_users."
done < <(cat liste1.txt)

