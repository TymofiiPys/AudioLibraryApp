import React, { useState } from "react";
import { apiRequest } from "../utils/apiHelper"; // Assuming this is where your API helper is

const Inventory = () => {
  const [date, setDate] = useState("");
  const [inventoryData, setInventoryData] = useState([]);
  const [error, setError] = useState(null);

  const handleDateChange = (e) => {
    setDate(e.target.value);
  };

  const handleFetchInventory = async () => {
    if (!date) {
      setError("Please select a date.");
      return;
    }
    
    setError(null); // Clear previous errors
    
    try {
      const response = await apiRequest(`/inventory/${date}`, "GET");
      setInventoryData(response); // Assume response is an array of inventory items
    } catch (err) {
      setError("Error fetching inventory data.");
      console.error(err);
    }
  };

  return (
    <div>
      <h1>Inventory</h1>
      
      <div>
        <label htmlFor="date">Select Date: </label>
        <input
          type="date"
          id="date"
          value={date}
          onChange={handleDateChange}
        />
        <button onClick={handleFetchInventory}>Fetch Inventory</button>
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {inventoryData.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Carrier ID</th>
              <th>Quantity</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {inventoryData.map((item) => (
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

export default Inventory;
