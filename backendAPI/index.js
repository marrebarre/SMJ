const express = require('express');
var app = express();
var bp = require('body-parser');
app.use(express.json());
const port = process.env.PORT || 8080;

//database
const { Pool } = require('pg');
const bodyParser = require('body-parser');
const pool = new Pool({
    connectionString: process.env.DATABASE_URL || "postgres://jhofskeupbweay:4c",
    ssl: {
        rejectUnauthorized: false
    }
});

// routing
app.post('/persons', async(req, res) => {
    var search = req.body.msg;
    var query = "SELECT * FROM person WHERE civic LIKE '%" + search + "%' OR name LIKE '%" + search + "%' order by civic";

    try {
        const client = await pool.connect();
        const result = await client.query(query);
        const results = { 'results': (result) ? result.rows : null };

        res.json(results);
        client.release();
    } catch (err) {
        console.error(err);
        res.send("Error from database " + err);
    }
});

app.post('/documents', async(req, res) => {
    var search = req.body.msg;
    var query = "SELECT * FROM document WHERE civicid = '" + search + "' order by timestamp";
    database(query, res);
});


app.post('/persons/add', async(req, res) => {
    var person = req.body;
    var query = "insert into person (name, civic, id) values ('" + person.name + "', '" + person.civic + "', '" + person.id + "')";
    database(query, res);
});

app.post('/documents/add', async(req, res) => {
    var doc = req.body;
    var query = "insert into document (timestamp, comment, id, civicid, doctorscomment) values ('" + doc.timestamp + "', '" + doc.comment + "', '" + doc.id + "', '" + doc.civicid + "', '" + doc.doctorscomment + "')";
    database(query, res);
});


app.post('/persons/edit', async(req, res) => {
    var person = req.body;
    var query = "update person set name = '" + person.name + "' where civic ='" + person.civic + "'";
    database(query, res);
});

app.post('/documents/edit', async(req, res) => {
    var doc = req.body;
    var query = "update document set comment = '" + doc.comment + "' where civicid = '" + doc.civicid + "' AND id = '" + doc.id + "'";
    database(query, res);
});

app.post('/login', async(req, res) => {
    var account = req.body;
    var query = "SELECT * FROM account where username= '" + account.username + "' AND password = '" + account.password + "'";

    try {
        const client = await pool.connect();
        const result = await client.query(query);
        const results = { 'results': (result) ? result.rows : null };

        if (results.results.length == 1) {
            res.send("OK");
        } else {
            res.send("Wrong credentials");
        }

        client.release();
    } catch (err) {
        console.error(err);
        res.send("Error from database " + err);
    }
});


app.get('/db', async(req, res) => {
    try {
        const client = await pool.connect();
        const result = await client.query('SELECT * FROM person');
        const results = { 'results': (result) ? result.rows : null };

        res.json(results);
        client.release();
    } catch (err) {
        console.error(err);
        res.send("Error from database " + err);
    }
});

async function database(query, res) {
    try {
        const client = await pool.connect();
        const result = await client.query(query);
        const results = { 'results': (result) ? result.rows : null };

        res.json(results);
        client.release();
    } catch (err) {
        console.error(err);
        res.send("Error from database " + err);
    }

};



app.listen(port, () => {
    console.log('Server running on port ' + port);
});