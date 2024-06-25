import React from 'react';
import { RiSearchLine } from 'react-icons/ri';

const SearchBar = ({ searchTerm, onSearch, onClearSearch, onUpload }) => {
  return (
    <div className="flex items-center mb-8">
      <div className="relative">
        <span className="absolute inset-y-0 left-0 pl-3 flex items-center text-gray-400">
          <RiSearchLine className="h-5 w-5" />
        </span>
        <input
          type="text"
          value={searchTerm}
          onChange={onSearch}
          className="block w-full rounded-lg pl-10 pr-4 py-2 border-gray-300 focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
          placeholder="Search documents..."
        />
      </div>
      <button
        onClick={onClearSearch}
        className="ml-2 px-4 py-2 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
      >
        Clear
      </button>
      <button
        onClick={onUpload}
        className="ml-2 px-4 py-2 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
      >
        Upload Document
      </button>
    </div>
  );
};

export default SearchBar;
