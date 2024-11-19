import React, { useState } from "react";
import { apiRequest } from "../utils/apiHelper"; // Assuming this is where your API helper is

const Sales = () => {
  const [date, setDate] = useState("");
  const [salesData, setSalesData] = useState([]);
  const [error, setError] = useState(null);

  const handleDateChange = (e) => {
    setDate(e.target.value);
  };

  const handleFetchSales = async () => {
    if (!date) {
      setError("Please select a date.");
      return;
    }

    setError(null); // Clear previous errors
    
    try {
      const response = await apiRequest(`/sales/${date}`, "GET");
      setSalesData(response); // Assume response is an array of sales data
    } catch (err) {
      setError("Error fetching sales data.");
      console.error(err);
    }
  };

  return (
    <div>
      <h1>Sales</h1>
      
      <div>
        <label htmlFor="date">Select Date: </label>
        <input
          type="date"
          id="date"
          value={date}
          onChange={handleDateChange}
        />
        <button onClick={handleFetchSales}>Fetch Sales</button>
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {salesData.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Carrier ID</th>
              <th>Sales Quantity</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {salesData.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.carrierId}</td>
                <td>{item.quantity}</td>
                <td>{item.date}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default Sales;
