from myDviz.utils.getAllData import getAllCars
def getTreeData():
        catalogue = getAllCars()['catalogue']

        tree_data = {}
        for car in catalogue:
            marque = car.marque
            nom = car.nom
            # puissance=car.puissance
            # longueur=car.longueur
            # nbPlaces=car.nbPlaces
            # nbPortes=car.nbPortes
            couleur = car.couleur
            occasion = car.occasion.lower()

            if marque not in tree_data:
                tree_data[marque] = {}

            if nom not in tree_data[marque]:
                tree_data[marque][nom] = {}
                # tree_data[marque][nom] = {
                #     'concatenated_nom': f"{nom} puissance: {car.puissance} longueur: {car.longueur} nbPlaces: {car.nbPlaces} nbPortes: {car.nbPortes}",
                #     'couleurs': {}}

            if couleur not in tree_data[marque][nom]:
                tree_data[marque][nom][couleur] = []

            tree_data[marque][nom][couleur].append({
                'occasion': occasion,
                'prix': car.prix
            })

        # formatted
        formatted_tree_data = []
        for marque, noms in tree_data.items():
            marque_data = {'name': marque, 'noms': []}
            for nom,couleurs in noms.items():
                nom_data = {'nom': nom, 'couleurs': []}
                for couleur, occasions in couleurs.items():
                    couleur_data = {'couleur': couleur, 'occasions': occasions}
                    nom_data['couleurs'].append(couleur_data)
                marque_data['noms'].append(nom_data)
            formatted_tree_data.append(marque_data)

        return formatted_tree_data
