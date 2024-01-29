strip_number=$1
url="http://xkcd.com/$strip_number/"

# Télécharger la page web
wget -q -O xkcd_page.html "$url"

# Extraire l'URL de l'image à partir de la page web
meta_line=$(grep -oP '<meta property="og:image" content="\K[^"]+' xkcd_page.html)
IFS=';' read -ra meta_parts <<< "$meta_line"
image_url="${meta_parts[0]}"

# Afficher l'URL de l'image
echo "URL de l'image : $image_url"

# Télécharger l'image
wget -q -O xkcd_strip.png "$image_url"

# Afficher l'image
xdg-open xkcd_strip.png

