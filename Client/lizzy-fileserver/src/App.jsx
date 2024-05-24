import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    
     <div className="App">
      <div className="sidebar">
        <div className="sidebar-icon home-icon"></div>
        <div className="sidebar-icon documents-icon"></div>
        <div className="sidebar-icon add-icon">+</div>
      </div>
      <div className="main-content">
        <div className="header">
          <input type="text" placeholder="Search for objects, contacts etc." />
          <div className="user-profile">
            <img src="user-avatar-url" alt="User Avatar" />
            <span>John Doe</span>
          </div>
        </div>
        <div className="documents-section">
          <div className="tabs">
            <button className="tab">All documents</button>
            <button className="tab active">Receipts</button>
            <button className="tab">Contracts</button>
            <button className="tab">Others</button>
            <button className="tab">Pre-categorized</button>
          </div>
          <div className="filter-bar">
            <input type="text" placeholder="Search documents" />
            <select>
              <option>All categories</option>
            </select>
            <select>
              <option>Period: All</option>
            </select>
            <select>
              <option>Document type: All</option>
            </select>
            <button>Search</button>
          </div>
          <div className="upload-section">
            <button>Choose file</button>
            <span>or drag file in here</span>
          </div>
          <div className="receipts-list">
            <div className="receipt">
              <input type="checkbox" />
              <div className="receipt-details">
                <span>DC_S1892_receipt</span>
                <span className="pdf-tag">PDF</span>
                <span>9MB</span>
              </div>
              <div className="user-details">
                <span className="initials">JD</span>
                <span>John Doe</span>
                <span>Address 11</span>
              </div>
              <div className="amount negative">-980.00€</div>
              <div className="category">Craftsman's Bill</div>
              <button className="download-button"></button>
              <button className="delete-button"></button>
            </div>
            <div className="receipt">
              <input type="checkbox" />
              <div className="receipt-details">
                <span>SD_D1245_receipt</span>
                <span className="pdf-tag">PDF</span>
                <span>15MB</span>
              </div>
              <div className="user-details">
                <span className="initials">JD</span>
                <span>John Doe</span>
                <span>Address 245, address 222</span>
              </div>
              <div className="amount positive">522.00€</div>
              <div className="category">Services</div>
              <button className="download-button"></button>
              <button className="delete-button"></button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default App
