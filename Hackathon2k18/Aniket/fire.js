(function(){
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyDZcLIz_1tbIUApRytA1ciq4r2lBU0PCNc",
    authDomain: "adch-9c89f.firebaseapp.com",
    databaseURL: "https://adch-9c89f.firebaseio.com",
    projectId: "adch-9c89f",
    storageBucket: "",
    messagingSenderId: "196006506550"
  };
  firebase.initializeApp(config);

//get elements
const txtemail=document.getElementById('email');
const txtpass=document.getElementById('pass');
const signin=document.getElementById('signin');
const signup=document.getElementById('signup');


//Add Login Activity
signin.addEventListener('click',e=>{
  //Get Email and Pass
  const email = txtemail.value;
  const pass = txtpass.value;
  const auth = firebase.auth();
//Sign In
  const promise = auth.signInWithEmailAndPassword(email, pass);
  if(promise)
  {
  window.location='Dashboard.html';
  }
  else
  {
  promise.catch(e => console.log(e.message));
  }
});

//Sign Up Activity
signup.addEventListener('click',e=>{
  //Get Email and Pass
  const email = txtemail.value;
  const pass = txtpass.value;
  const auth = firebase.auth();
//Sign Up
  const promise = auth.createUserWithEmailAndPassword(email, pass);
  promise.catch(e => console.log(e.message));
});





}());