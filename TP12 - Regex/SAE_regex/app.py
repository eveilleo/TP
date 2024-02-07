from flask import Flask, render_template, request
import re

app = Flask(__name__)

@app.route('/')
def home():
    return 'Bienvenue sur la page d\'accueil'

@app.route('/newuser/', methods=['GET', 'POST'])
def new_user():
    if request.method == 'POST':
        utilisateur = request.form['utilisateur']
 
        if re.match(r"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,}$", utilisateur):
            message = "L'identifiant respecte les critères spécifiés."
        else:
            message = "L'identifiant ne respecte pas les critères spécifiés."

        return render_template('newuser.html', message=message)

    return render_template('newuser.html', message=None)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
