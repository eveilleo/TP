if [ "$1" = 1 ]; then
    echo "Veuillez fournir une première lettre en argument."
    exit 1
fi

premiere_lettre="$1"

if [ ! -d "/etc" ]; then
    echo "/etc n'existe pas ou n'est pas un répertoire valide."
    exit 1
fi

for fichier in /etc/"$premiere_lettre"*; do
    if [ -e "$fichier" ]; then
        type_fichier=$(file -b "$fichier")
        echo "Fichier : $fichier"
        echo "Type de fichier : $type_fichier"
        echo
    fi
done

