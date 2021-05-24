# SMJ
Sebastian Martin Journal

Heroku: https://smj-backend.herokuapp.com/
Login credentials for testing purpose:
username: doctor
password: admin

SMJ är ett journalsystem där man loggar in som en användare och kan söka i vårt system på namn och personnummer för att få upp personens journal.
Varje journal (dokument) har ett id och en person kan ha flera journaler. När man söker i listan på en person får man upp alla journaler som man sedan kan trycka in på och läsa ens journal. 
Detta kommer bara upp om personen finns i systemet som man knappar in. Vi har valt att man inte kan ändra dokument då det är känsliga filer, det ska inte finnas någon risk att ta bort dokument.

Vår tekniska lösning av SMJ.

BACKEND Vår backend liknar en REST-API som är bygger på Node.js med framework express tillsammans med JavaScript. Vår backend är ett fristående applikation från vår UI/frontend. 
webbservern/API har olika routing funktioner som använder sig utav HTTP calls som kommer från frontend/UI. Vi använder oss utav post request då frontend skickar data som backend/api använder.
Varför vi kallar vår backend för en REST API är för att det krävs inga externa bibliotek eller liknande för att använda vår API, utan ändast följa våra routs. Detta kan vilken enhet som helst göra som är ansluten till internet.
Vi har valt att arbete med Heroku för att deploya vår backend cloudbased. Det som sker via Heroku Cloud så pushar man sin lokala git repo till ett remote git repo som körs via Heroku. Herko har git körandes på sina maskiner. 
Det som sker när vi har deployat vår backend till Heroku är att de startar upp vår backend applikation, vår git repo som körs på herouks maskiner så att vår API blir tillgänglig hela tiden. 

DATABASE Vi använder PostgreSQL som databas då detta var de ända gratis alternativ addons till Heroku Cloud. PostgreSQL är en databas som är väl ettablerad idag. Vi började först att skapa en lokal database via postgresql. Men gick sedan över till heroku när vi hade kopplat heroku's addon till postgresql: "Heroku Postgres Hobby Dev postgresql-triangular-14817" vilket är vår remote database. För att komma åt vår databas remote använder vi kommandot "heroku pg:psql" som används av heroku CLI. Med detta kommandot får vi tillgång till databasen där vi connectar till den. Där vi sedan kan skjustera och bygga vår databas. För att ansluta vår API till vår remote databas används object pooling för att spara själva connection till databasen som sedan återanvänds.

FRONTEND Vår frontend är byggd i java med hjälp av javaFX för att få fram vårt gränssnitt. Vår applikation låter användaren integrera med vårt journalsystem där det krävs ett inlog. När man loggar in skickas username och password till vår API där API:n sedan hämtar info från account i databasen för att se att det är rätt uppgifter - om det är det blir det en lyckad inloggning.
Vår applikation följer vår APIs routings för att kunna få fram den efterfrågade informationen från vår remote databas. Vi har använt oss utav post calls då det alltid är att vår frontend applikation skickar någon slags information till vår backend som sedan får ett respond. 
Vår frontend applikation gör det möjligt för användaren att lista alla personer som finns i vårt system -> trycka in på en specifik person för att se alla ens journaler -> sedan laddas själva dokument journalen in och man kan läsa den specifika dokumentet.

DEPLOYMENT vi har använt oss utav Heroku Cloud för att deploya vår backend cloudbaserat.

Vi sikta på en 4a i detta arbete. Tanken bakom collection of statistics function/data är våra journaler/dokument för varje person.

Skapad av Martin Malmberg & Sebastian Mårtensson
