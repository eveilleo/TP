from flask import Flask, render_template, request
import re

app = Flask(__name__)

@app.route('/')
def home():
    return 'Bienvenue sur la page d\'accueil'

@app.route('/newuser/', methods=['GET', 'POST'])
def new_user():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        mail = request.form['mail']
 
        # Critères regex pour l'identifiant
        if re.match(r"^[a-z]{6,10}$", username):
            message_username = "L'identifiant respecte les critères spécifiés."
        else:
            message_username = "L'identifiant ne respecte pas les critères spécifiés."

        # Critères regex pour le mot de passe
        if re.match(r"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[%#{}@]).{6,}$", password):
            message_password = "Le mot de passe respecte les critères spécifiés."
        else:
            message_password = "Le mot de passe ne respecte pas les critères spécifiés."

        # Critères regex pour l'adresse email
        if re.match(r'^[A-Za-z0-9_\-\.]+@[A-Za-z0-9\-\.]+$', mail):
            message_mail = "L'adresse email est valide."
        else:
            message_mail = "L'adresse email n'est pas valide."

        return render_template('newuser.html', message_username=message_username, message_password=message_password, message_mail=message_mail)

    return render_template('newuser.html')

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
