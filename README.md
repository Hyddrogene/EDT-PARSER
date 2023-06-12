# EDT-Parser
Ce projet à pour but d'agréger toutes les informations nécessaires à la conception d'un emploi du temps universitaire dans un fichier XML.
Ces informations sont récupérées depuis différentes sources dont les principales sont les différentes API procurées par la DDN et un fichier de configuration écrit directement par le client.

## Sommaire

 1. [Contenu du dépôt](#contenu-du-dépôt)
 2. [Modélisation de l'emploi du temps](#modélisation-de-lemploi-du-temps)
 3. [Fichier de configuration](#fichier-de-configuration)

## Contenu du dépôt
Le dépôt est scindé en 2 parties. La première est le [parser](https://github.com/adelestre/edt-parser/tree/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-parser/src/main/java/com/leria/parser) lui-même, rassemblant toutes les classes utile à la récupération des données et à leur modélisation au format XML. La [seconde partie](https://github.com/adelestre/edt-parser/tree/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-api) contient 2 fichiers. L'un est un [fichier SQL](https://github.com/adelestre/edt-parser/blob/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-api/edt-api.sql) permettant la création et  avec une base de données contenant les différentes informations qui ne sont pas encore procurées par la DDN. Le second est un [Web Project](https://github.com/adelestre/edt-parser/blob/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-api/edt-api.war) (WAR) permettant l'interaction avec cette base de données sous la forme d'une API.

## Modélisation de l'emploi du temps
Il existe dans le projet 2 différentes modélisations utilisées simultanément. La [première modélisation](https://github.com/adelestre/edt-parser/tree/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-parser/src/main/java/com/leria/parser/Models/Leria) correspond au modèle utilisée par le laboratoire LERIA. C'est d'après ce modèle que toutes les informations sont assemblées pour former la sortie finale. Elle est inspirée de ce [schéma](https://ua-usp.github.io/timetabling/assets/schema/usp_timetabling_v0_3.xsd) dont la [documentation](https://ua-usp.github.io/timetabling/schema) s'applique tout autant à cette modélisation. La [seconde modélisation](https://github.com/adelestre/edt-parser/tree/2d4e3aa9bbea6ce1b9070e89a837e9d37d195418/edt-parser/src/main/java/com/leria/parser/Models/UA) est un mappage directe des informations récupérées des API de la DDN. Elle sert principalement à faire le pont entre ces API et le premier modèle.

## Fichier de configuration
Le fichier de configuration est un fichier XML qui permet de donner un contexte à l'emploi du temps ainsi que certains éléments nécessaires à sa création. Un [schema XSD](https://github.com/adelestre/edt-parser/blob/3c912e67b97b2238e1d1a12334ef2f6eeda93fe6/edt-parser/src/main/java/com/leria/parser/Config/schema_config.xsd) est disponible, toutefois voici le détail de ce qu'on y retrouve :

 - Un élément `<name>` donnant un nom à l'emploi du temps ainsi qu'au fichier final. (requis)
 - Un élément `<year>` permettant de renseigner l'année à laquelle on s'intéresse. Cette année sera passé aux API pour déterminer les jeux de données à aller chercher. (requis)
 - 
