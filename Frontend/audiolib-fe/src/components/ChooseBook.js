import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { apiRequest } from "../utils/apiHelper";

const ChooseBook = () => {
  const [books, setBooks] = useState([]);
  const [search, setSearch] = useState("");
  const navigate = useNavigate();

  // Fetch books when the component mounts or search changes
  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const searchParams = new URLSearchParams();
        if (search) {
          searchParams.append("name", search);
        }

        const data = await apiRequest(`/books?${searchParams.toString()}`, "GET");
        setBooks(data); // Set the fetched books
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    };

    fetchBooks();
  }, [search]); // Re-run the effect when search term changes

  const handleSearchChange = (e) => {
    setSearch(e.target.value); // Update search term
  };

  const handleBookClick = (bookId) => {
    navigate(`/book/${bookId}`); // Redirect to the book's detail page
  };

  return (
    <div className="choose-book">
      <h2>Choose Book</h2>
      <div>
        <input
          type="text"
          placeholder="Search by name"
          value={search}
          onChange={handleSearchChange}
        />
      </div>
      <ul>
        {books.map((book) => (
          <li key={book.id} onClick={() => handleBookClick(book.id)}>
            <h3>{book.name}</h3>
            <p>{book.author}</p>
            <p>{book.publisher}</p>
            <p>{book.year}</p>
            <p>{book.genre}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ChooseBook;
