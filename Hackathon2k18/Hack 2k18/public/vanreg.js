(function(){

  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyDZcLIz_1tbIUApRytA1ciq4r2lBU0PCNc",
    authDomain: "adch-9c89f.firebaseapp.com",
    databaseURL: "https://adch-9c89f.firebaseio.com",
    projectId: "adch-9c89f",
    storageBucket: "adch-9c89f.appspot.com",
    messagingSenderId: "196006506550"
  };
  firebase.initializeApp(config);


//get elements
const fname=document.getElementById('fname');
const mname=document.getElementById('mname');
const lname=document.getElementById('lname');
const email=document.getElementById('email');
const phone=document.getElementById('phone');
const rcno=document.getElementById('rcno');
const urcbook=document.getElementById('urcbook');
const vechileno=document.getElementById('vechileno');
const liceneno=document.getElementById('liceneno');
const ulicene=document.getElementById('ulicene');
const aadhar=document.getElementById('aadhar');
const uaadhar=document.getElementById('uaadhar');
const insurance=document.getElementById('insurance');
const signup=document.getElementById('signup');
const rcloader=document.getElementById('rcloader');
const noc=document.getElementById('noc');


     


signup.addEventListener('click', e=>{

var fname1=fname.value;
console.log(fname1);
var mname1=mname.value;
console.log(mname1);
var lname1=lname.value;
console.log(lname1);
var email1=email.value;
console.log(email1);
var phone1=phone.value;
console.log(phone1);
var rcno1=rcno.value;
console.log(rcno1);
var vechileno1=vechileno.value;
console.log(vechileno1);
var liceneno1=liceneno.value;
console.log(liceneno1);
var aadhar1=aadhar.value;
console.log(aadhar1);
var insurance1=insurance.value;
console.log(insurance1);
var write = firebase.database().ref().child('Register').child(vechileno1).child('info');
write.set({fname: fname1,mname: mname1,lname: lname1,email: email1,phone: phone1,rcno: rcno1,vechileno: vechileno1,liceneno: liceneno1,aadhar: aadhar1,insurance: insurance1});

alert('Thank you Registration Completed');
window.location='index.html';
});



//for Rc file upload
urcbook.addEventListener('change', function(e){
    //get file
var vechileno1=vechileno.value;
//console.log(email1);
 var file = e.target.files[0];

    //create a storage ref
var storageRef = firebase.storage().ref('Registration/' + file.name);

    //upload file
var task = storageRef.put(file);

    //update progress bar
task.on('state_changed',

        function progress(snapshot){
         
        },
        function error(err){

        },
        function complete(){
            console.log('ok');
            var write = firebase.database().ref().child('Register').child(vechileno1).child('doc').child('rcbook');
            write.set({image:task.snapshot.downloadURL});
                    
        }

    );

});

//for Licene file upload
ulicene.addEventListener('change', function(e){
    //get file
var vechileno1=vechileno.value;
 var file = e.target.files[0];

    //create a storage ref
var storageRef = firebase.storage().ref('Registration/' + file.name);

    //upload file
var task = storageRef.put(file);

    //update progress bar
task.on('state_changed',

        function progress(snapshot){
         
        },
        function error(err){

        },
        function complete(){
            console.log('ok');
            var write = firebase.database().ref().child('Register').child(vechileno1).child('doc').child('licene');
            write.set({image:task.snapshot.downloadURL});
                    
        }

    );

});

//for aadhar file upload
uaadhar.addEventListener('change', function(e){
    //get file
var vechileno1=vechileno.value;
 var file = e.target.files[0];

    //create a storage ref
var storageRef = firebase.storage().ref('Registration/' + file.name);

    //upload file
var task = storageRef.put(file);

    //update progress bar
task.on('state_changed',

        function progress(snapshot){
         
        },
        function error(err){

        },
        function complete(){
            console.log('ok');
            var write = firebase.database().ref().child('Register').child(vechileno1).child('doc').child('aadhar');
            write.set({image:task.snapshot.downloadURL});
                    
        }

    );

});


//for noc police clereance file upload
noc.addEventListener('change', function(e){
    //get file
var vechileno1=vechileno.value;
 var file = e.target.files[0];

    //create a storage ref
var storageRef = firebase.storage().ref('Registration/' + file.name);

    //upload file
var task = storageRef.put(file);

    //update progress bar
task.on('state_changed',

        function progress(snapshot){
         
        },
        function error(err){

        },
        function complete(){
            console.log('ok');
            var write = firebase.database().ref().child('Register').child(vechileno1).child('doc').child('noc');
            write.set({image:task.snapshot.downloadURL});
              $('#signup').show();       
        }

    );

});


}());