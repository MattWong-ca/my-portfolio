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
    // Fetches JSON from server and turns it into JSON
    const responseFromServer = await fetch('/JSONarray');
    const jsonArray = await responseFromServer.json();

    // Prints out entire JSON array as well as 2nd element into console
    console.log(jsonArray);
    console.log(jsonArray[1]);

    // Sets text in jsonContainer to be entire JSON array
    const jsonContainer = document.getElementById('json-container');
    jsonContainer.innerText = jsonArray;
}

//Lists out form responses by iterating through ArrayList
async function testing() {
    // Fetches JSON from server and turns it into JSON
    const responseFromServer = await fetch('/form-responses');
    const responsesList = await responseFromServer.json();

    // Prints out entire JSON array
    console.log(responsesList);
    console.log(responsesList[0].message)

    // Sets text in jsonContainer to be entire JSON array
    const formContainer = document.getElementById('form-container');
    // formContainer.innerText = responsesList[0].message;
    for (let i = 0; i < responsesList.length; i++) {
        formContainer.innerText += responsesList[i].message + "---";
    }
}

function loadTasks() {
    fetch('/list-tasks').then(response => response.json()).then((tasks) => {
        const taskListElement = document.getElementById('task-list');
        tasks.forEach((task) => {
            taskListElement.appendChild(createTaskElement(task));
        })
    });
}
/** Creates an element that represents a task, including its delete button. */
function createTaskElement(task) {
    const taskElement = document.createElement('li');
    taskElement.className = 'task';

    const titleElement = document.createElement('span');
    titleElement.innerText = task.title;

    const deleteButtonElement = document.createElement('button');
    deleteButtonElement.innerText = 'Delete';
    deleteButtonElement.addEventListener('click', () => {
        deleteTask(task);

        // Remove the task from the DOM.
        taskElement.remove();
    });

    taskElement.appendChild(titleElement);
    taskElement.appendChild(deleteButtonElement);
    return taskElement;
}

/** Tells the server to delete the task. */
function deleteTask(task) {
    const params = new URLSearchParams();
    params.append('id', task.id);
    fetch('/delete-task', { method: 'POST', body: params });
}