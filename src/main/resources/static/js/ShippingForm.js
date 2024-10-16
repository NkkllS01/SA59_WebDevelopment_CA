import React, { useState, useEffect, useRef } from "react";
import axios from "axios";

const REST_API_USER_URL = "http://localhost:8080/api/userShipping";
const REST_API_SHIPPING_URL = "http://localhost:8080/api/shipping";

// Team03.Kuo Chi
export default function CreateShipping() {
    // the shippingData state holds the form's data, with initial value of ""
	const [shippingData, setShippingData] = useState({
		name: "",
		email: "",
		address: "",
		city: "",
		postalCode: "",
	});


	const addressElement = useRef(null);
	const cityElement = useRef(null);
	const postalCodeElement = useRef(null);

    // calls the function retrieveUserData to fetch data from the server once
	useEffect(() => {
		console.log("Retrieving user data from server");
		retrieveUserData();
	}, []);

    // sends a GET request to fetch user data *see ShippingController
	function retrieveUserData() {
		axios
			.get(REST_API_USER_URL, { withCredentials: true })
			// if successful, sets shippingData with the response data and logs on the console
			.then((response) => {
				setShippingData(response.data);
				console.log(response.data);
			})
			// if failed, logs error on the console
			.catch((e) => {
				console.log(e);
			});
	}

    // upon click on the Confirm button, sends a POST request to server *see ShippingController
	function handleCreateClick(e) {
		e.preventDefault();
		// log for reference
		console.log("Confirm button is clicked");
		console.log("Address:", addressElement.current.value);
		console.log("City:", cityElement.current.value);
		console.log("Postal Code:", postalCodeElement.current.value);

        // only sending the required data back to server
		const limitedData = {
			address: shippingData.address,
			city: shippingData.city,
			postalCode: shippingData.postalCode,
		};

        // log for reference
		console.log("Limited Data:", limitedData);
		console.log("Shipping data before sending:", JSON.stringify(limitedData));

        // sends a POST request
		axios
			.post(REST_API_SHIPPING_URL, limitedData, {
				headers: {
					"Content-Type": "application/json",
				},
				withCredentials: true,
			})
			// if successful, redirect to /payment URL *see PaymentController
			.then((response) => {
				console.log("Sending shipping data to:", REST_API_SHIPPING_URL);
				console.log("Shipping data successfully sent:", response.data);
				window.location.href = "http://localhost:8080/payment";
			})
			// if failed, logs error on the console
			.catch((error) => {
				console.log("Sending shipping data to:", REST_API_SHIPPING_URL);
				console.error("Error sending shipping data:", error);
			});
	}

    // updates the shippingData state whenever a user updates the relevant fields
	function handleInputChange(e) {
		const { name, value } = e.target;
		setShippingData((prevState) => ({
			...prevState,
			[name]: value,
		}));
	}

    // rendering the form, user unable to update the fields "name" and "email", but able to update the shipping address
	return (
		<div>
			<h3>Shipping Details</h3>
			<form>
				<label htmlFor="name">Customer Name: </label>
				<input type="text" name="name" value={shippingData.name} disabled />
				<br />
				<label htmlFor="email">Email: </label>
				<input type="text" name="email" value={shippingData.email} disabled />
				<br />
				<label htmlFor="address">Address: </label>
				<input
					type="text"
					name="address"
					value={shippingData.address}
					ref={addressElement}
					onChange={handleInputChange}
				/>
				<br />
				<label htmlFor="city">City: </label>
				<input
					type="text"
					name="city"
					value={shippingData.city}
					ref={cityElement}
					onChange={handleInputChange}
				/>
				<br />
				<label htmlFor="postalCode">Postal Code: </label>
				<input
					type="text"
					name="postalCode"
					value={shippingData.postalCode}
					ref={postalCodeElement}
					onChange={handleInputChange}
				/>
				<br />
				<button onClick={handleCreateClick}>Confirm</button>{" "}
			</form>
		</div>
	);
}
