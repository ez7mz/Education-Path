import smtplib  # © Hamza Mesrar Groupe
from fct import *

# contenu de mail message : ==============================================

str_msg = "My name is MESRAR HAMZA,  head of the EZ TEAM. I sent you this email to inform you that your project for this week is :  "

book_link = "https://bit.ly/3GQczXq"

# ================================================================

# entrez vos informations de "compte de connexion" et verifiez votre acces au programme

admin_user, admin_pass = info_verf()

# =========================================================================

# Configuration de serveur SMTP et connecter au serveur

smtpObj = smtplib.SMTP("smtp.gmail.com", 587)
smtpObj.ehlo()
smtpObj.starttls()
smtpObj.login(admin_user, admin_pass)
ver_server_statu(smtpObj.login(admin_user, admin_pass)[0])

# ============================================================

# donner l'ordre au programme pour debut de envoyer les email

command = input("type ' Start ' to give the program the order to send the emails: ")
while command.lower() != "start":
    print("Error! Try again")
    time.sleep(1.5)
    os.system("CLS")
    command = input("type ' Start ' to give the program the order to send the emails: ")

# =====================================================================

# def de la fonction assign_Task :

emails = (
    email_list()
)  # : extraire le dictionnaire qui contient les emails. "deja stocké"

# le dic emails contient les informations des membres avec l'email comme key de chaque member (Voir fct.py)
# exp : {"email1@gmail.com" : (nom,prenom)}


def assign_task():
    Tasks = tasks_list()  # : extraire la liste des tache. "deja stocké"
    for email in emails.keys():
        random_task = random.choice(
            Tasks
        )  # rand.choice : tirer un element au hasard parmi une liste d'elemnts
        email_dict[
            email
        ] = random_task  # Stocker cette tache dans un dictionnaire avec l'email de membre comme key.
        Tasks.remove(
            random_task
        )  # supprimer la tache dans la liste pour éviter la redondance
    for email in email_dict:
        message = str(
            "Subject: Week Project\n"
            + "Hi {} {},\n\n".format(
                emails[email][0], emails[email][1]
            )  # c'est juste le format de message à envoyer.
            + str_msg
            + email_dict[email]
            + "\n\n\nBook Link : {}".format(book_link)
        )
        print(
            emails[email][0],
            emails[email][1]
            + " \t\tVotre Projet est : \t\t"
            + email_dict[email],  # pour l'impression dans la console pour vérifier
        )
        smtpObj.sendmail(
            admin_user, email, message
        )  # envoyer les emails aux membres, en utilisant la méthode sendmail ()


email_dict = {}

# ======================================================================================================

# planifier le programme pour envoyer les taches aux membres chaque semaine

for i in range(52):
    assign_task()
    time.sleep(7 * 86400)

# Déconnexion du serveur SMTP
smtpObj.quit()
