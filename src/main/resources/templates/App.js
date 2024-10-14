import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ShippingForm from './ShippingForm';

function App() {
    return (
        <Router>
            <Switch>
                <Route exact path="/shipping" component={ShippingForm} />
            </Switch>
        </Router>
    );
}

export default App;