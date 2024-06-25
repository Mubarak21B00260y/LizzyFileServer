import React from 'react';
import { CiSearch } from 'react-icons/ci';

const SearchBar = ({ searchTerm, handleSearch, clearSearch }) => {
  return (
    <div className="flex items-center mb-8">
      <div className="relative">
        <span className="absolute inset-y-0 left-0 pl-3 flex items-center">
          <CiSearch className="h-5 w-5 text-gray-400" />
        </span>
        <input
          type="text"
          value={searchTerm}
          onChange={handleSearch}
          className="block w-full rounded-lg pl-10 pr-4 py-2 border-gray-300 focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
          placeholder="Search documents..."
        />
      </div>
      <button
        onClick={clearSearch}
        className="ml-2 px-4 py-2 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
      >
        Clear
      </button>
    </div>
  );
};

export default SearchBar;
