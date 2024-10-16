import React, { useState, useEffect } from "react";

export default function Header() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [cartItems, setCartItems] = useState(0);
    const [user, setUser] = useState(null);
    const cssURL = "http://localhost:8080/css/custom-style-jm.css";

    useEffect(() => {
        // Fetch user login status and cart items via API
        fetch("/api/user")
            .then((response) => response.json)
            .then((data) => {
                if (data.isLoggedIn) {
                    setIsLoggedIn(true);
                    setUser(data.user);
                }
            });
    }, []);

    return (
        <nav
            className="navbar navbar-expand-sm navbar-dark"
            style={{ backgroundColor: "#FF7101" }}>
            <link rel="stylesheet" href={cssURL} />
            <div className="container-fluid">
                <div className="d-flex align-items-center">
                    {/* Logo */}
                    <a className="navbar-brand" href="#">
                        <img
                            src="/images/Logo.png"
                            style={{ width: "40px" }}
                            alt="logo"
                            className="rounded"
                        />
                        <strong>
							<span className="ms-2 text-white" style={{ fontSize: "20pt" }}>
								OrangeStore
							</span>
                        </strong>
                    </a>

                    {/* Navbar links */}
                    <ul className="navbar-nav ms-3 text-white fw-bold">
                        <li className="nav-item">
                            <a
                                className="nav-link text-white link-hover"
                                href="http://localhost:8080/orangestore/home">
                                Home
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link text-white link-hover" href="/about">
                                About
                            </a>
                        </li>
                        <li className="nav-item dropdown">
                            <a
                                className="nav-link dropdown-toggle text-white link-hover"
                                href="#"
                                role="button"
                                data-bs-toggle="dropdown">
                                Contact us
                            </a>
                            <ul className="dropdown-menu dropdown-menu-dark">
                                <li>
                                    <a className="dropdown-item" href="#">
                                        E-mail: orangeestore@org.com
                                    </a>
                                </li>
                                <li>
                                    <a className="dropdown-item" href="#">
                                        Tel: +86 8888 6666
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <div className="d-flex align-items-center ms-auto">
                    {/* Search form */}
                    <form
                        className="d-flex mx-3"
                        action="http://localhost:8080/orangestore/all/products/searching"
                        method="POST">
                        <div className="input-group">
                            <input
                                type="text"
                                className="form-control"
                                name="keyword"
                                placeholder="Search Product"
                                aria-label="Search"
                            />
                            <button
                                className="btn btn-outline-light btn-search"
                                type="submit">
                                <i className="fas fa-search fa-lg"></i>
                            </button>
                        </div>
                    </form>
                    {/* User account dropdown or login icon */}
                    {isLoggedIn ? (
                        <div className="dropdown">
                            <a
                                href="#"
                                className="mx-3 text-white icon-hover dropdown-toggle"
                                id="loggedInUserMenu"
                                data-bs-toggle="dropdown"
                                aria-expanded="false">
                                <i className="fas fa-user fa-2x"></i>
                            </a>
                            <ul
                                className="dropdown-menu dropdown-menu-end"
                                aria-labelledby="loggedInUserMenu">
                                <li>
                                    <a
                                        className="dropdown-item"
                                        href="http://localhost:8080/myaccount/profile/update">
                                        My Profile
                                    </a>
                                </li>
                                <li>
                                    <a
                                        className="dropdown-item"
                                        href="http://localhost:8080/orders">
                                        View Order History
                                    </a>
                                </li>
                                <li>
                                    <hr className="dropdown-divider" />
                                </li>
                                <li>
                                    <a
                                        className="dropdown-item"
                                        href="http://localhost:8080/logout">
                                        Logout
                                    </a>
                                </li>
                            </ul>
                        </div>
                    ) : (
                        <a
                            href="#"
                            className="mx-3 text-white icon-hover"
                            aria-label="Login"
                            data-bs-toggle="modal"
                            data-bs-target="#loginModal">
                            <i className="fas fa-user fa-2x"></i>
                        </a>
                    )}

                    {/* Shopping cart */}
                    <a
                        href="http://localhost:8080/cart"
                        className="mx-3 text-white icon-hover"
                        aria-label="Shopping Cart">
                        <i className="fas fa-shopping-cart fa-2x"></i>
                    </a>
                </div>
            </div>
        </nav>
    );
}