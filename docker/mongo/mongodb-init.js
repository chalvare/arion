db = db.getSiblingDB('ipandreu');

db.createCollection('customers');

db.customers.insertMany([
    {
        "name": "Christian",
        "motorbikes": [{
            "model": "R1250GS",
            "cv": 135
        }],
        "age": 37
    }
]);