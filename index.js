var SERVER_NAME = 'patients-api'
var PORT = 5000;
var HOST = '127.0.0.1';


var restify = require('restify')

  // Get a persistence engine for the patients and tests
  , patientsSave = require('save')('patients')
  , testsSave = require('save')('tests')

  // Create the restify server
  , server = restify.createServer({ name: SERVER_NAME})

  server.listen(PORT, HOST, function () {
  console.log('Server %s listening at %s', server.name, server.url)
  console.log('Resources:')
  console.log(' /patients')
  console.log(' /patients/:id')
  console.log(' /patients/:id/tests')  
})

server
  // Allow the use of POST
  .use(restify.fullResponse())

  // Maps req.body to req.params so there is no switching between them
  .use(restify.bodyParser())

// Get all patients in the system
server.get('/patients', function (req, res, next) {

  // Find every entity within the given collection
  patientsSave.find({}, function (error, patients) {

    // Return all of the patients in the system
    res.send(patients)
  })
})

// Get a single patient by their patient id
server.get('/patients/:id', function (req, res, next) {

  // Find a single patient by their id within save
  patientsSave.find({ _id: req.params.id }, function (error, patient) {

    // If there are any errors, pass them to next in the correct format
    if (error) return next(new restify.InvalidArgumentError(JSON.stringify(error.errors)))

    if (patient) {
      // Send the patient if no issues
      res.send(patient)
    } else {
      // Send 404 header if the patient doesn't exist
      res.send(404)
    }
  })
})

// Create a new patients
server.post('/patients', function (req, res, next) {

  // Adding validation to enter values 
  if (req.params.first_name === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('First name must be supplied'))
  }
  if (req.params.last_name === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Last name must be supplied'))
  }
  if (req.params.address === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Address must be supplied'))
  }
  if (req.params.date_of_birth === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Date of birth must be supplied'))
  }
  if (req.params.department === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Department must be supplied'))
  }
  if (req.params.doctor === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Doctor name must be supplied'))
  }

  // Creating new patient record 
  var newpatients = {
		first_name: req.params.first_name, 
		last_name: req.params.last_name,
    address: req.params.address,
    date_of_birth: req.params.date_of_birth,
    department: req.params.department,
    doctor: req.params.doctor,
	}

  // Create the patients using the persistence engine
  patientsSave.create( newpatients, function (error, patients) {

    // If there are any errors, pass them to next in the correct format
    if (error) return next(new restify.InvalidArgumentError(JSON.stringify(error.errors)))

    // Send the patients if no issues
    res.send(201, patients)
  })
})

// Get tests for patients with given id in the system
server.get('/patients/:id/tests', function (req, res, next) {

  // Find every entity within the given collection
  testsSave.find({ patient_id: req.params.id }, function (error, tests) {

    // Return all of the patients in the system
    res.send(tests)
  })
})

// Create a new tests for patient
server.post('/patients/:id/tests', function (req, res, next) {

  // Adding validation to enter values 
  if (req.params.date === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Test date must be supplied'))
  }
  if (req.params.nurse_name === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Nurse name must be supplied'))
  }
  if (req.params.type === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Test type must be supplied'))
  }
  if (req.params.category === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Test Category must be supplied'))
  }
  if (req.params.readings === undefined ) {
    // If there are any errors, pass them to next in the correct format
    return next(new restify.InvalidArgumentError('Test readings must be supplied'))
  }
  
  // Creating new test record 
  var newtests = {
    patient_id: req.params.id,
		date: req.params.date, 
		nurse_name: req.params.nurse_name,
    type: req.params.type,
    category: req.params.category,
    readings: req.params.readings,

	}

  // Create the tests using the persistence engine
  testsSave.create( newtests, function (error, tests) {

    // If there are any errors, pass them to next in the correct format
    if (error) return next(new restify.InvalidArgumentError(JSON.stringify(error.errors)))

    // Send the tests if no issues
    res.send(201, tests)
  })
})

// Delete all patients
server.del('/patients', function (req, res, next) {

  // Delete the patient with the persistence engine
  patientsSave.deleteMany(req.params, function (error, patients) {

    // If there are any errors, pass them to next in the correct format
    if (error) return next(new restify.InvalidArgumentError(JSON.stringify(error.errors)))

    // Send a 200 OK response
    res.send()
  })
})

// Delete patient with the given id
server.del('/patients/:id', function (req, res, next) {

  // Delete the patient with the persistence engine
  patientsSave.delete(req.params.id, function (error, patients) {

    // If there are any errors, pass them to next in the correct format
    if (error) return next(new restify.InvalidArgumentError(JSON.stringify(error.errors)))

    // Send a 200 OK response
    res.send()
  })
})

// Delete all tests of current patient
server.del('/patients/:id/tests', function (req, res, next) {

  // Delete the patient with the persistence engine
  testsSave.deleteMany({patient_id:req.params.id}, function (error, tests) {

    // If there are any errors, pass them to next in the correct format
    if (error) return next(new restify.InvalidArgumentError(JSON.stringify(error.errors)))

    // Send a 200 OK response
    res.send()
  })
})


