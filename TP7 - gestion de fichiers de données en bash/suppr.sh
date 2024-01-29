while IFS= read -r username; do
  if id "$username" &>/dev/null; then
    deluser "$username"
    echo "L'utilisateur $username a été supprimé."
  else
    echo "L'utilisateur $username n'existe pas."
  fi
done < liste1.txt

