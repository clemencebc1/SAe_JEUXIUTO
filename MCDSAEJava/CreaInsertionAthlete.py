import csv

# Define the CSV file path
csv_file_path = 'donnees.csv'
output_file_path = 'sql_insertions.txt'

# Initialize SQL statements

sql_insert_athlete = []
sql_insert_participer = []
id_A = 1

# Read the CSV file
with open(csv_file_path, newline='') as csvfile:
    csv_reader = csv.DictReader(csvfile, delimiter=',')
    
    for row in csv_reader:
        # Extract data from each row
        nom = row['Nom']
        prenom = row['Prénom']
        sexe = row['Sexe']
        pays = row['Pays']
        epreuve = row['Épreuve']
        force = row['Force']
        endurance = row['Endurance']
        agilite = row['agilite']
        
        if pays == "France":
            id_P = 1
        elif pays == "Chine":
            id_P = 2
        elif pays == "Japon":
            id_P = 3
        elif pays == "USA":
            id_P = 4
        elif pays == "Kenya":
            id_P = 5
        elif pays == "Maroc":
            id_P = 6
        elif pays == "Allemagne":
            id_P = 7
        elif pays == "Australie":
            id_P = 8
        elif pays == "Brésil":
            id_P = 9
        elif pays == "Turquie":
            id_P = 10 


        if epreuve ==  "Volley-Ball":
            id_S = 4
            if sexe == "M":
                id_Ep = 7
            else:
                id_Ep = 8

        elif epreuve ==  "Natation 100m brasse":
            id_S = 1
            if sexe == "M":
                id_Ep = 1
            else:
                id_Ep = 2

        elif epreuve ==  "Natation 4x100m nage libre relais":
            id_S = 1
            if sexe == "M":
                id_Ep = 3
            else:
                id_Ep = 4

        elif epreuve ==  "Handball":
            id_S = 5
            if sexe == "M":
                id_Ep = 5
            else:
                id_Ep = 6

        elif epreuve ==  "Escrime fleuret":
            id_S = 3
            if sexe == "M":
                id_Ep = 9
            else:
                id_Ep = 10

        elif epreuve ==  "Escrime épée":
            id_S = 3
            if sexe == "M":
                id_Ep = 11
            else:
                id_Ep = 12

        elif epreuve ==  "Athétisme 110 haies":
            id_S = 2
            if sexe == "M":
                id_Ep = 13
            else:
                id_Ep = 14

        elif epreuve ==  "Athlétisme relais 400m":
            id_S = 2
            if sexe == "M":
                id_Ep = 15
            else:
                id_Ep = 16

        elif id_S == 5:
            if id_P == 1:
                if sexe == "M":
                    id_E = 3
                else:
                    id_E = 4
            elif id_P == 2:
                if sexe == "M":
                    id_E = 11
                else:
                    id_E = 12
            elif id_P == 3:
                if sexe == "M":
                    id_E = 19
                else:
                    id_E = 20
            elif id_P == 4:
                if sexe == "M":
                    id_E = 27
                else:
                    id_E = 28
            elif id_P == 5:
                if sexe == "M":
                    id_E = 35
                else:
                    id_E = 36
            elif id_P == 6:
                if sexe == "M":
                    id_E = 43
                else:
                    id_E = 44
            elif id_P == 7:
                if sexe == "M":
                    id_E = 51
                else:
                    id_E = 52
            elif id_P == 8:
                if sexe == "M":
                    id_E = 60
                else:
                    id_E = 61
            elif id_P == 9:
                if sexe == "M":
                    id_E = 68
                else:
                    id_E = 69
            elif id_P == 10:
                if sexe == "M":
                    id_E = 76
                else:
                    id_E = 77

        if id_S == 4:
            if id_P == 1:
                if sexe == "M":
                    id_E = 5
                else:
                    id_E = 6
            elif id_P == 2:
                if sexe == "M":
                    id_E = 13
                else:
                    id_E = 14
            elif id_P == 3:
                if sexe == "M":
                    id_E = 21
                else:
                    id_E = 22
            elif id_P == 4:
                if sexe == "M":
                    id_E = 29
                else:
                    id_E = 30
            elif id_P == 5:
                if sexe == "M":
                    id_E = 37
                else:
                    id_E = 38
            elif id_P == 6:
                if sexe == "M":
                    id_E = 45
                else:
                    id_E = 46
            elif id_P == 7:
                if sexe == "M":
                    id_E = 53
                else:
                    id_E = 54
            elif id_P == 8:
                if sexe == "M":
                    id_E = 62
                else:
                    id_E = 63
            elif id_P == 9:
                if sexe == "M":
                    id_E = 70
                else:
                    id_E = 71
            elif id_P == 10:
                if sexe == "M":
                    id_E = 78
                else:
                    id_E = 79
            
            elif id_Ep == 3:
                if id_P == 1:
                     id_E = 2
                elif id_P == 2:
                     id_E = 10
                elif id_P == 3:
                     id_E = 18
                elif id_P == 4:
                     id_E = 26
                elif id_P == 5:
                     id_E = 34
                elif id_P == 6:
                     id_E = 42
                elif id_P == 7:
                     id_E = 50
                elif id_P == 8:
                     id_E = 59
                elif id_P == 9:
                     id_E = 67
                else:
                     id_E = 75

            if id_Ep == 4:
                if id_P == 1:
                     id_E = 1
                elif id_P == 2:
                     id_E = 9
                elif id_P == 3:
                     id_E = 17
                elif id_P == 4:
                     id_E = 25
                elif id_P == 5:
                     id_E = 33
                elif id_P == 6:
                     id_E = 41
                elif id_P == 7:
                     id_E = 49
                elif id_P == 8:
                     id_E = 58
                elif id_P == 9:
                     id_E = 66
                else:
                     id_E = 74

            if id_Ep == 15:
                if id_P == 1:
                     id_E = 7
                elif id_P == 2:
                     id_E = 15
                elif id_P == 3:
                     id_E = 23
                elif id_P == 4:
                     id_E = 31
                elif id_P == 5:
                     id_E = 39
                elif id_P == 6:
                     id_E = 47
                elif id_P == 7:
                     id_E = 55
                elif id_P == 8:
                     id_E = 64
                elif id_P == 9:
                     id_E = 72
                else:
                     id_E = 80
            if id_Ep == 16:
                if id_P == 1:
                     id_E = 8
                elif id_P == 2:
                     id_E = 16
                elif id_P == 3:
                     id_E = 24
                elif id_P == 4:
                     id_E = 32
                elif id_P == 5:
                     id_E = 40
                elif id_P == 6:
                     id_E = 48
                elif id_P == 7:
                     id_E = 56
                elif id_P == 8:
                     id_E = 65
                elif id_P == 9:
                     id_E = 73
                else:
                     id_E = 81

        
        

        
        sql_insert_athlete.append(f"INSERT INTO ATHLETE (id_A, nom_A, prenom_A, sexe_A, id_P, force_A, endurance, agilite, id_E) VALUES ({id_A}, '{nom}', '{prenom}', '{sexe}', {id_P}, {force}, {endurance}, {agilite}, {id_E});")
        sql_insert_participer.append(f"INSERT INTO PARTICIPER (id_A, id_Ep) VALUES ({id_A}, {id_Ep});")
        id_A += 1

        
with open(output_file_path, 'w') as outfile:
    for sql in sql_insert_athlete + sql_insert_participer:
        outfile.write(sql + '\n')

print(f"SQL insertions have been written to {output_file_path}")
