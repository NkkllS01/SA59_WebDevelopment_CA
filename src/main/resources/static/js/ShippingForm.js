import React, { useState, useEffect, useRef } from "react";
import axios from "axios";

const REST_API_USER_URL = "http://localhost:8080/api/userShipping";
const REST_API_SHIPPING_URL = "http://localhost:8080/api/shipping";

export default function CreateShipping() {
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

	useEffect(() => {
		console.log("Retrieving user data from server");
		retrieveUserData();
	}, []);

	function retrieveUserData() {
		axios
			.get(REST_API_USER_URL, { withCredentials: true })
			.then((response) => {
				setShippingData(response.data);
				console.log(response.data);
			})
			.catch((e) => {
				console.log(e);
			});
	}

	function handleCreateClick(e) {
		e.preventDefault();
		console.log("Confirm button is clicked");
		console.log("Address:", addressElement.current.value);
		console.log("City:", cityElement.current.value);
		console.log("Postal Code:", postalCodeElement.current.value);

		const limitedData = {
			address: shippingData.address,
			city: shippingData.city,
			postalCode: shippingData.postalCode,
		};

		console.log("Limited Data:", limitedData);
		console.log("Shipping data before sending:", JSON.stringify(limitedData));

		axios
			.post(REST_API_SHIPPING_URL, limitedData, {
				headers: {
					"Content-Type": "application/json",
				},
				withCredentials: true,
			})
			.then((response) => {
				console.log("Sending shipping data to:", REST_API_SHIPPING_URL);
				console.log("Shipping data successfully sent:", response.data);
				window.location.href = "http://localhost:8080/payment";
			})
			.catch((error) => {
				console.log("Sending shipping data to:", REST_API_SHIPPING_URL);
				console.error("Error sending shipping data:", error);
			});
	}

	function handleInputChange(e) {
		const { name, value } = e.target;
		setShippingData((prevState) => ({
			...prevState,
			[name]: value,
		}));
	}

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
