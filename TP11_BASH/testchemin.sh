IFS="/"
read -r -a dirs <<< "$1"

current_path="/"
index=1

while [ $index -lt ${#dirs[@]} ]; do
	dir="${dirs[index]}" # La variable dir contient le nom du dossier à chaque itération de la boucle
	current_path="$current_path$dir/" # Construit progressivement le chemin complet du dossier courant dans le chemin spécifié
	echo "current=$current_path"
	if [ ! -d "$current_path" ]; then
		echo "Chemin invalide, le dossier $dir n'existe pas dans $current_path"
		exit 1
	fi

	((index++))
done

echo "Chemin valide"
