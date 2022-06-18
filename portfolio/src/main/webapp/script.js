// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

//Adds random fun fact about me to the page inside funFactContainer
function funFactsAboutMe() {
  const funFacts =
      [' huge Toronto Raptors and Blue Jays fan! 🏀 ⚾',
        ' iOS Developer @ theScore! 📱',
        ' budding entrepreneur! 🚀',
        ' Web3 enthusiast! 🔮'];

    const funFact = funFacts[Math.floor(Math.random() * funFacts.length)];
    
    const funFactContainer = document.getElementById('funFacts-container');
    funFactContainer.innerText = funFact;
}

//Adds current server time to the page inside dateContainer
async function showServerTime() {
    const responseFromServer = await fetch('/date');
    const textFromResponse = await responseFromServer.text();

    const dateContainer = document.getElementById('date-container');
    dateContainer.innerText = textFromResponse;
}

//Adds JSON data to the page inside jsonContainer
async function fetchJSON() {
    const responseFromServer = await fetch('/JSONarray');
    const jsonArray = await responseFromServer.json();
    // const jsonArray = await responseFromServer.text();
    // console.log(jsonArray[0].name);
    console.log(jsonArray[1]);

    const jsonContainer = document.getElementById('json-container');
    // jsonContainer.innerText = jsonArray[0].name;
    // jsonContainer.HTML = jsonArray;
    jsonContainer.innerText = jsonArray[1];
}






// /** Fetches stats from the server and adds them to the page. */
// async function getServerStats() {
//     // The json() function returns an object that contains fields that we can
//     // reference to create HTML.

//     const statsListElement = document.getElementById('server-stats-container');
//     statsListElement.innerHTML = '';

//     statsListElement.appendChild(
//         createListElement('Start time: ' + stats.startTime));
//     statsListElement.appendChild(
//         createListElement('Current time: ' + stats.currentTime));
//     statsListElement.appendChild(
//         createListElement('Max memory: ' + stats.maxMemory));
//     statsListElement.appendChild(
//         createListElement('Used memory: ' + stats.usedMemory));
// }

// /** Creates an <li> element containing text. */
// function createListElement(text) {
//     const liElement = document.createElement('li');
//     liElement.innerText = text;
//     return liElement;
// }