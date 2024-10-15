export default function Footer() {
    const cssURL = "http://localhost:8080/css/custom-style-jm.css";
    return (
        <div>
            <link rel="stylesheet" href={cssURL} />
            <footer
                id="sticky-footer"
                className="flex-shrink-0 py-4"
                style={{ backgroundColor: "orange", color: "white" }}>
                <div className="container text-center">
                    <small>Copyright &copy; Orange Store</small>
                </div>
            </footer>
        </div>
    );
}