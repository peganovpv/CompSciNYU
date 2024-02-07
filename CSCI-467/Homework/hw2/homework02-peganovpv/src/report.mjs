// report.mjs

import fs from 'fs';
import { parse } from 'csv-parse';
import path from 'path';

import { longestFunFact, getTitlesByYear, actorCounts } from './sfmovies.mjs';

const filePath = path.join('src', 'data', 'Film_Locations_in_San_Francisco.csv');

fs.readFile(filePath, 'utf8', (err, data) => {

  if (err) {
    console.error('Error reading the file:', err);
    return;
  }
  
  parse(data, {}, (err, records) => {

    if (err) {
      console.error('Error parsing the CSV data:', err);
      return;
    }
    const headers = records.shift();

    const movies = records.map(row => {
      return row.reduce((acc, value, index) => {
        acc[headers[index]] = value;
        return acc;
      }, {});

    });
    
    console.log(movies);

  });

});
