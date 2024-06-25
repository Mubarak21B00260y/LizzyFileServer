import React from 'react';

const Pagination = ({ currentPage, paginate, totalDocuments, itemsPerPage }) => {
  const totalPages = Math.ceil(totalDocuments / itemsPerPage);

  return (
    <div className="flex justify-center mt-8">
      <nav className="inline-flex rounded-md shadow-sm -space-x-px" aria-label="Pagination">
        <button
          onClick={() => paginate(currentPage - 1)}
          disabled={currentPage === 1}
          className={`bg-white border border-gray-300 rounded-l-md px-3 py-2 text-sm font-medium ${currentPage === 1 ? 'text-gray-500' : 'text-blue-500 hover:bg-gray-50 hover:text-blue-700'}`}
        >
          Previous
        </button>
        {Array.from({ length: totalPages }, (_, i) => (
          <button
            key={i + 1}
            onClick={() => paginate(i + 1)}
            className={`bg-white border-t border-b border-gray-300 px-3 py-2 text-sm font-medium ${currentPage === i + 1 ? 'text-white bg-blue-500' : 'text-gray-500 hover:bg-gray-50 hover:text-blue-700'}`}
          >
            {i + 1}
          </button>
        ))}
        <button
          onClick={() => paginate(currentPage + 1)}
          disabled={totalDocuments <= currentPage * itemsPerPage}
          className={`bg-white border border-gray-300 rounded-r-md px-3 py-2 text-sm font-medium ${totalDocuments <= currentPage * itemsPerPage ? 'text-gray-500' : 'text-blue-500 hover:bg-gray-50 hover:text-blue-700'}`}
        >
          Next
        </button>
      </nav>
    </div>
  );
};

export default Pagination;
