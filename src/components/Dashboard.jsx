import React from "react";
const App = () => {
    let data = localStorage.getItem('customer');
    data = JSON.parse(data);
    return (
        <div>
            <p>{data.email}</p>
        </div>
    );
};
export default App;


