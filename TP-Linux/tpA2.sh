if [ $# -eq 0 ]; then
    echo "Veuillez fournir un nom d'utilisateur en argument."
    exit 1
fi

utilisateur_actuel="$USER"

if [ "$1" = "$utilisateur_actuel" ]; then
    echo "OUI"
else
    echo "NON"
fi
