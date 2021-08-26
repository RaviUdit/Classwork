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
 
    fields.customerName = document.getElementById('customerName');
    
    console.log("fields: customer name " + fields.customerName.value);
    
    fields.email = document.getElementById('customerEmail');
    
    console.log("fields: email " + fields.email.value);
    
    fields.phoneNumber = document.getElementById('customerPhone');
    
    console.log("fields: phone number " + fields.phoneNumber.value);
    
    fields.roi = document.getElementById('roi');
    
    console.log("fields: ROI " + fields.roi.value);
    
    fields.addInfo = document.getElementById('addInfo');
    
    console.log("fields: Add Info " + fields.addInfo.value);
    
    fields.sunday = document.getElementById('sunday');
    fields.monday = document.getElementById('monday');
    fields.tuesday = document.getElementById('tuesday');
    fields.wednesday = document.getElementById('wednesday');
    fields.thursday = document.getElementById('thursday');
    fields.friday = document.getElementById('friday');
    fields.saturday = document.getElementById('saturday');
    
})

function previousVisit(){
    return document.querySelector("input[name='answer']:checked")
}

function isNotEmpty(value) {
    
    if (value == null || typeof value == 'undefined') return false;
    
    return (value.length > 0);
}

function isNumber(num){
    
    console.log("num =  " + num);
    
    return (num.length > 0) && !isNaN(num);
}

function isValidEmail(email){
    
    let regex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    
    console.log("RegEx Test  " + regex.test(String(email).toLowerCase()));
    
    return regex.test(String(email).toLowerCase());
}

function validateField(field, validationFunc){
    
    if (field == null) return false;
    
    let isFieldValid = validationFunc(field.value);
    
    if (!isFieldValid){
        field.className = 'error';
    } else {
        field.className = '';
    }
    
    return isFieldValid;
}

function isValidCustomer(){
    
    var valid = true;
    
    valid &= validateField(fields.customerName, isNotEmpty);
    console.log(`is customer valid: ${valid}`);
    
    valid &= validateField(fields.email, isValidEmail);
    console.log(`is email valid: ${valid}`);
    
    valid &= validateField(fields.phoneNumber, isNumber);
    console.log(`is phone number valid: ${valid}`);
    
    valid &= validateField(fields.roi, isNotEmpty);
    console.log(`is roi valid: ${valid}`);
    
    valid &= validateField(fields.addInfo, isNotEmpty);
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
        /*  this.sunday = sunday;
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
    
    if ( isValidCustomer()){
        var cust = new Customer(fields.customerName, fields.email, fields.phoneNumber, fields.roi, fields.addInfo, 
            previousVisit.checked);

        alert ('Thanks for reaching out!')
    } else {
        alert('Information Missing! Please Try Again.')
    }
        
    console.log("cust =" + cust ); 
}
