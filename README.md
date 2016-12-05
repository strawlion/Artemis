# Artemis

1) Add dataset

Add a data folder to the project root, next to the src folder.
Add a CSV file representing each OMOP CDM v5 table (e.g. death.csv consisting of person_id,death_date,death_type_concept_id,cause_concept_id,cause_source_value,cause_source_concept_id)

2) Start the server

From the project root, run the following command:

Mac
`sbt/sbt compile run`

Windows
`sbt\sbt.bat compile run`

3) Install client dependencies

Install node.js and the node package manager command line utilities 
https://nodejs.org/en/
https://www.npmjs.com/

Navigate to the client folder and run `npm install` to install client dependencies

4) Start the client

Start the client by running `npm start` from the client folder
