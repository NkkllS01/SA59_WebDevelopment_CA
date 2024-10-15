import React, { useState, useEffect, useRef } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import axios from 'axios';

const REST_API_USER_URL = "http://localhost:8080/api/userShipping";
const REST_API_SHIPPING_URL = "http://localhost:8080/api/shipping";

export default function CreateShipping() {
    const [shippingData, setShippingData] = useState({
            name: "",
            email: "",
            address: "",
            city: "",
            postalCode: ""
        });

    const addressElement = useRef();
    const cityElement = useRef();
    const postalCodeElement = useRef();

    useEffect(  () => {
        console.log("Retrieving user data from server");
        retrieveUserData();
    },  []);

    function retrieveUserData() {
        axios
            .get(REST_API_USER_URL)
            .then(response => {
                setShippingData(response.data)
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    function handleCreateClick(e) {
        e.preventDefault();
        console.log("Confirm button is clicked");
        console.log("Address:", addressElement.current.value);
        console.log("City:", cityElement.current.value);
        console.log("Postal Code:", postalCodeElement.current.value);

        axios
        .post(REST_API_SHIPPING_URL, shippingData, {
        headers: {
            'Expect': ''
          }
        })
        .then(response => {
            console.log("Shipping data successfully sent:", response.data);
            window.location.href = "/payment.html";
        })
        .catch(error => {
            console.error("Error sending shipping data:", error);
        });
    }

    function handleInputChange(e) {
            const { name, value } = e.target;
            setShippingData(prevState => ({
                ...prevState,
                [name]: value
            }));
        }

    function validateFields() {
            let validationErrors = {};

            if (!shippingData.address) {
                validationErrors.address = "Address is required";
            }

            if (!shippingData.city) {
                validationErrors.city = "City is required";
            }

            if (!shippingData.postalCode) {
                validationErrors.postalCode = "Postal code is required";
            }

            setErrors(validationErrors);
            return Object.keys(validationErrors).length === 0;

    return (
        <div>
            <h3>Shipping Details</h3>
            <form>
                <label htmlFor='name'>Customer Name: </label>
                <input type='text' name='name' value={shippingData.name} disabled/>
                <br/>
                <label htmlFor='email'>Email: </label>
                <input type='text' name='email' value={shippingData.email} disabled/>
                <br/>
                <label htmlFor='address'>Address: </label>
                <input type='text' name='address' value={shippingData.address} onChange={handleInputChange} />
                {errors.address && <span style={{ color: 'red' }}>{errors.address}</span>}
                <br/>
                <label htmlFor='city'>City: </label>
                <input type='text' name='city' value={shippingData.address} onChange={handleInputChange} />
                {errors.city && <span style={{ color: 'red' }}>{errors.city}</span>}
                <br/>
                <label htmlFor='postalCode'>Postal Code: </label>
                <input type='text' name='postalCode' value={shippingData.address} onChange={handleInputChange} />
                {errors.postalCode && <span style={{ color: 'red' }}>{errors.postalCode}</span>}
                <br/>
            <button onClick={handleCreateClick}>Confirm</button> </form>
        </div>
    )
}