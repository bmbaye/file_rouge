//A ajouter
=============JPA=================
  *pom.xml
  *========= entityManager ===========>Database
  entityManager factory ===============>Database

  ==========Demarche=====
    a.Installer les dependances
    b. Repo entity
    c. Implementation du reposi
    
==============================Suivi de cours============
(Tout les fichiers à charger doivent se trouver dans le dossier resources)
-dans le dossier resources creer le dossier META-INF dans lequel je cree persistence.xml et le charger (demander a chatgpt *preciser le dialect a utiliser 'mysql, potsgres')
-Installer les dependances hibernate core et hibernate entity dans le pom.xml
-dans les entitiess: @Entity au-dessus de nos classes ( pour dire que ces classe deoivent etre creer en base de donnees), @Table(name = [le nom dans la bd]
