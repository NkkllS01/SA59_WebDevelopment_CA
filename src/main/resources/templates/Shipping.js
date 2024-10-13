import React, { useState } from 'react';
import axios from 'axios';

const ShippingForm = () => {
    //Starts with a blank form
    const [formData, setFormData] = useState({
        address: '',
        city: '',
        postalCode: ''
    });

    // State to handle any errors from server or input validation
    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState('');

    // Handle form input changes
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors({});
        setSuccessMessage('');

        try {
            // Make POST request to the backend
            const response = await axios.post('http://localhost:8080/shipping', formData, {
                headers: {
                    'Content-Type': 'application/json',
                },
                withCredentials: true, // Include cookies/session data
            });

            if (response.status === 201) {
                setSuccessMessage('Shipping details successfully submitted!');
            }
        } catch (error) {
            if (error.response && error.response.status === 400) {
                setErrors({ server: error.response.data });
            } else {
                setErrors({ server: 'Something went wrong. Please try again.' });
            }
        }
    };

    return (
        <div>
            <h2>Enter Shipping Details</h2>
            {errors.server && <p style={{ color: 'red' }}>{errors.server}</p>}
            {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Address:</label>
                    <input
                        type="text"
                        name="address"
                        value={formData.address}
                        onChange={handleChange}
                        required
                    />
                </div>
                {errors.address && <p style={{ color: 'red' }}>{errors.address}</p>}
                <div>
                    <label>City:</label>
                    <input
                        type="text"
                        name="city"
                        value={formData.city}
                        onChange={handleChange}
                        required
                    />
                </div>
                {errors.city && <p style={{ color: 'red' }}>{errors.city}</p>}
                <div>
                    <label>Postal Code:</label>
                    <input
                        type="text"
                        name="postalCode"
                        value={formData.postalCode}
                        onChange={handleChange}
                        required
                    />
                </div>
                {errors.postalCode && <p style={{ color: 'red' }}>{errors.postalCode}</p>}
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>
        </div>
    );
};

export default ShippingForm;