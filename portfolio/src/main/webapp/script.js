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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['I am a huge Toronto Raptors and Blue Jays fan', 'I am currently working at theScore as an iOS Developer!', 'I want to pursue entrepreneurship in the future!', 'I am interested in web3!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

async function showServerTime() {
    const responseFromServer = await fetch('/date');
    const textFromResponse = await responseFromServer.text();
  
    const dateContainer = document.getElementById('date-container');
    dateContainer.innerText = textFromResponse;
  }

  async function test() {
    const responseFromServer = await fetch('/experience');
    // const textFromResponse = await responseFromServer.text();
    const hi = await responseFromServer.json();
    console.log(hi[0].name);
    console.log("Hi");

    const dateContainer = document.getElementById('exp');
    // dateContainer.innerText = textFromResponse;
    dateContainer.innerText = hi[0].name;
  }
