// sfmovies.mjs

function longestFunFact(data) {

    return data.reduce((longest, current) => {
      return (current['Fun Facts'] && current['Fun Facts'].length > (longest['Fun Facts'] ? longest['Fun Facts'].length : 0)) ? current : longest;
    }, {});

}
  
function getTitlesByYear(data, year) {

    const titles = data

      .filter(movie => movie['Release Year'] == year)
      .map(movie => `${movie.Title.toUpperCase()} (${year}).`)
      .filter((title, index, self) => self.indexOf(title) === index);

    return titles;
}
  
function actorCounts(data) {

    const counts = {};
    data.forEach(movie => {
      ['Actor 1', 'Actor 2', 'Actor 3'].forEach(actorField => {
        const actor = movie[actorField];
        if (actor) {
          counts[actor] = counts[actor] ? counts[actor] + 1 : 1;
        }
      });
    });

    return counts;

}

export { longestFunFact, getTitlesByYear, actorCounts };
  