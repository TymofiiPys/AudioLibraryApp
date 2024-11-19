import React, { useState } from "react";
import { apiRequest } from "../utils/apiHelper"; // Assuming this is where your API helper is

const PopularBooks = () => {
  const [amt, setAmt] = useState(5); // Default to 5 entries
  const [booksData, setBooksData] = useState([]);
  const [error, setError] = useState(null);

  const handleAmtChange = (e) => {
    setAmt(e.target.value);
  };

  const handleFetchPopularBooks = async () => {
    if (amt <= 0) {
      setError("Please specify a valid number of entries.");
      return;
    }

    setError(null); // Clear previous errors

    try {
      const response = await apiRequest(`/top_books?top=${amt}`, "GET");
      setBooksData(response); // Assume response is an array of popular books
    } catch (err) {
      setError("Error fetching popular books.");
      console.error(err);
    }
  };

  return (
    <div>
      <h1>Most Popular Books</h1>

      <div>
        <label htmlFor="amt">Number of Entries: </label>
        <input
          type="number"
          id="amt"
          value={amt}
          onChange={handleAmtChange}
          min="1"
        />
        <button onClick={handleFetchPopularBooks}>Fetch Popular Books</button>
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {booksData.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Book Name</th>
              <th>Author</th>
              <th>Listened</th>
            </tr>
          </thead>
          <tbody>
            {booksData.map((book) => (
              <tr key={book.id}>
                <td>{book.id}</td>
                <td>{book.name}</td>
                <td>{book.author}</td>
                <td>{book.listened}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default PopularBooks;
