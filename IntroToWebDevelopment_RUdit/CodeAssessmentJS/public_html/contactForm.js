/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Test console message to make sure js files is being called correctly.
 * 
 */

console.log('it works')

/*
 * Initiate Variables
 * 
 */

var fields={};

document.addEventListener("DOMContentLoaded", function(){ //DOMContent waits for the html page to fully load before
                                                          //population these variables.
 
    fields.customerName = document.getElementById('customerName').value;
    
    console.log("fields: customer name " + fields.customerName);
    
    fields.email = document.getElementById('customerEmail').value;
    
    console.log("fields: email " + fields.email);
    
    fields.phoneNumber = document.getElementById('customerPhone').value;
    
    console.log("fields: phone number " + fields.phoneNumber);
    
    fields.roi = document.getElementById('roi').value;
    
    console.log("fields: ROI " + fields.roi);
    
    fields.addInfo = document.getElementById('addInfo').value;
    
    console.log("fields: Add Info " + fields.addInfo);
    
    fields.sunday = document.getElementById('sunday');
    fields.monday = document.getElementById('monday');
    fields.tuesday = document.getElementById('tuesday');
    fields.wednesday = document.getElementById('wednesday');
    fields.thursday = document.getElementById('thursday');
    fields.friday = document.getElementById('friday');
    fields.saturday = document.getElementById('saturday');
    
})

function previousVisit(){
    
    return document.querySelector('input[id="answer"]:checked')
}

function isNotEmpty(value) {
    
    if (value == null || typeof value == 'undefined') return false;
    
    return (value.length > 0);
}

function isNumber(num){
    
    var num = fields.phoneNumber;
    
    console.log("num =  " + num);
    
    return (num.length > 0) && !isNaN(num);
}

function isValidEmail(email){
    
    var email = fields.email;
    
    let regex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    
    console.log("RegEx Test  " + regex.test(String(email).toLowerCase()));
    
    return regex.test(String(email).toLowerCase());
}

function fieldVal(field, validationFunc){
    
    if (field == null) return false;
    
    let isFieldValid = validationFunc(field.value);
    
    if (!isFieldValid){
        field.className = 'placeHolderRed';
        } else {
        field.className = '';
    }
    
    return isFieldValid;
}

function validCustomer(){
    
    var valid = true;
    
    valid &= fieldVal(fields.customerName, isNotEmpty);
    valid &= fieldVal(fields.email, isValidEmail);
    valid &= fieldVal(fields.phoneNumber, isNumber);
    valid &= fieldVal(fields.roi, isNotEmpty);
    valid &= fieldVal(fields.addInfo, isNotEmpty)    ;
    
    console.log("valid "+valid);
    
    return valid; 
}

class Customer{
    
    constructor (customerName, email, phoneNumber, roi, addInfo, previousVisit){
            
            this.customerName = customerName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.roi = roi; 
            this.addInfo = addInfo; 
            this.prevVisit = previousVisit;
        /*    this.sunday = sunday;
            this.monday = monday;
            this.tuesday = tuesday;
            this.wednesday = wednesday;
            this.thursday = thursday;
            this.friday = friday;
            this.saturday = saturday; */
    }
}

function contactUs(){
    
    fields.prevVisit = previousVisit(); 
    
    if ( validCustomer()){
        var cust = new Customer(customerName.value, email.value, phoneNumber.value, roi.value, addinfo.value, 
            previousVisit.checked);
             
            alert ('Thanks for reaching out!')
        } else {
            alert('Information Missing! Please Try Again.')
        }
        
                  console.log(cust)  
}


