#!/bin/bash

# Chemin vers le fichier journal pour enregistrer les sorties de debsecan
LOG_FILE="$HOME/debsecan.log"

# Exécuter debsecan et enregistrer la sortie dans le fichier journal
sudo debsecan --suite $(lsb_release --codename --short) >> "$LOG_FILE" 2>&1
