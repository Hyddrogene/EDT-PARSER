# EDT-Parser
Ce projet a pour but d'agréger toutes les informations nécessaires à la conception d'un emploi du temps universitaire dans un fichier XML.
Ces informations sont récupérées depuis différentes sources dont les principales sont les différentes API procurées par la DDN et un fichier de configuration écrit directement par le client.

## Sommaire

 1. [Contenu du dépôt](#contenu-du-dépôt)
 2. [Modélisation de l'emploi du temps](#modélisation-de-lemploi-du-temps)
 3. [Fichier de configuration](#fichier-de-configuration)
 4. [Les requêtes API](#les-requêtes-api)
 5. [Le parser](#le-parser)
 6. [Les services API fournis](#les-services-api-fournis)

## Contenu du dépôt
Le dépôt est scindé en 2 parties. La première est le [parser](https://github.com/adelestre/edt-parser/tree/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-parser/src/main/java/com/leria/parser) lui-même, rassemblant toutes les classes utiles à la récupération des données et à leur modélisation au format XML. La [seconde partie](https://github.com/adelestre/edt-parser/tree/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-api) contient 2 fichiers. L'un est un [fichier SQL](https://github.com/adelestre/edt-parser/blob/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-api/edt-api.sql) permettant la création d'une base de données contenant les différentes informations qui ne nous sont pour le moment pas encore procurées. Le second est une compression du [Web Project](https://github.com/adelestre/edt-parser/blob/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-api/edt-api.war) (WAR) permettant l'interaction avec cette base de données sous la forme d'une API. Cette seconde partie est une solution temporaire, locale et probablement peu robuste.

## Modélisation de l'emploi du temps
Il existe dans le projet 2 différentes modélisations utilisées simultanément. La [première modélisation](https://github.com/adelestre/edt-parser/tree/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-parser/src/main/java/com/leria/parser/Models/Leria) correspond au modèle utilisé par le laboratoire LERIA. C'est d'après ce modèle que toutes les informations sont assemblées pour former la sortie finale. Elle est inspirée de ce [schéma](https://ua-usp.github.io/timetabling/assets/schema/usp_timetabling_v0_3.xsd) dont la [documentation](https://ua-usp.github.io/timetabling/schema) s'applique tout autant à cette modélisation. La [seconde modélisation](https://github.com/adelestre/edt-parser/tree/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-parser/src/main/java/com/leria/parser/Models/UA) est un mappage directe des informations récupérées des API de la DDN. Elle sert principalement à faire le pont entre ces API et le premier modèle.

## Fichier de configuration
Le fichier de configuration est un fichier XML qui permet de donner un contexte à l'emploi du temps ainsi que certains éléments nécessaires à sa création. Un [schema XSD](https://github.com/adelestre/edt-parser/blob/3c912e67b97b2238e1d1a12334ef2f6eeda93fe6/edt-parser/src/main/java/com/leria/parser/Config/schema_config.xsd) est disponible, toutefois voici le détail de ce qu'on y retrouve :

 - Un élément `<name>` donnant un nom à l'emploi du temps ainsi qu'au fichier de sortie. (requis)
 - Un élément `<year>` permettant de renseigner l'année à laquelle on s'intéresse. Cette année sera passé aux API pour déterminer les jeux de données à aller chercher. (requis)
 - Un élément `<nrDaysPerWeek>` pour fixer le nombre de jours de travail par semaine. (requis)
 - Un élément `<nrSlotsPerDay>` représentant le nombre de slots disponibles dans une journée en minutes. (requis)
 - Un élément `<etapes>` contenant une liste d'éléments `<etape>`. Ces éléments représentent les étapes que l'on souhaite modéliser. Les étapes ne figurant pas dans cette liste seront filtrés.
 
 Les éléments `<etape>` doivent disposer des attributs suivants :
 - un id, celui de l'étape à représenter.
 - un label pour aider tout ce qui n'est pas machine à les identifier.
 - une liste de code périodes sous forme de chaîne de caractères permettant de filtrer sur les périodes. La chaîne accepte toutes les périodes. (Si vous ne souhaitez traiter aucune période, n'ajoutez simplement pas l'étape dans la liste d'étapes `<etapes>`.) 
 - un effectif, permettant d'indiquer le nombre d'étudiants inscrits à l'étape (important)

## Les requêtes API
Vous trouverez dans edt-parser un [package API ](https://github.com/adelestre/edt-parser/tree/7186ca60bcaf233bbace3b5d6be0f409d83ed888/edt-parser/src/main/java/com/leria/parser/Api) regroupant les fonctions d'appels aux différentes API. Dans ce package se trouve une classe [API](https://github.com/adelestre/edt-parser/blob/7186ca60bcaf233bbace3b5d6be0f409d83ed888/edt-parser/src/main/java/com/leria/parser/Api/API.java) permettant la création des requêtes, notamment leur encodage et leur paramétrage. Vous trouverez aussi la classe [UAKey](https://github.com/adelestre/edt-parser/blob/7186ca60bcaf233bbace3b5d6be0f409d83ed888/edt-parser/src/main/java/com/leria/parser/Api/UAKey.java) dans laquelle vous devrez saisir votre clé si vous souhaitez utiliser les API de la DDN.

## Le Parser
Le package le plus important et évidemment le [package parser](https://github.com/adelestre/edt-parser/tree/7186ca60bcaf233bbace3b5d6be0f409d83ed888/edt-parser/src/main/java/com/leria/parser/Parser). Il contient toute l'orchestration et la logique du programme. C'est ici que sont appelées les différentes API et que les différents calculs permettant de déterminer des informations importantes sont réalisés. Dans ce package se trouve la classe [parser](https://github.com/adelestre/edt-parser/blob/7186ca60bcaf233bbace3b5d6be0f409d83ed888/edt-parser/src/main/java/com/leria/parser/Parser/Parser.java) s'occupant de l'orchestration. Toutes les autres classes figurant dans ce package permette de gérer la récupération et la modifications des informations pour un élément précis. À l'exception de la classe [LabelFormatter](https://github.com/adelestre/edt-parser/blob/7186ca60bcaf233bbace3b5d6be0f409d83ed888/edt-parser/src/main/java/com/leria/parser/Parser/LabelFormatter.java) qui est utilisé tout au long du traitement afin de s'assurer que les labels ne contiennent pas de caractères interdits ou réservés.

## Les services API fournis
Le dépôt contient toute une partie dédiée à des API temporaires. L'archive [Web Project](https://github.com/adelestre/edt-parser/blob/7186ca60bcaf233bbace3b5d6be0f409d83ed888/edt-api/edt-api.war) (WAR) est importable sous [Eclipse](https://www.eclipse.org/downloads/) et a été prévu pour fonctionner sur un serveur [Apache Tomcat v9.0](https://tomcat.apache.org/download-90.cgi). Figure dans cet application 3 packages, beans, dao et services. Beans contient les différents modèles représentant les informations stockées dans la base de données. Dao comprend les classes permettant les accès aux couches persistantes. Enfin les classes de services établissent les URL d'accès aux API.

Si vous recontrez des difficultés à importer le projet depuis le fichier .war sous Eclipse, vérifiez que les bibliothèques présentent dans "src/main/webapp/WEB-INF/lib" figurent bien dans le classpath (Project > Properties > Java Build Path > Libraries > Classpath > add JARs...)  

## Contact
Si jamais des questions subsistent : adrien.delestre@hotmail.com
