![Logo](https://github.com/rhit-koenigjl/CSSE375-team15/blob/main/src/main/resources/images/menu_logo.png?raw=true)
### Indiana Ghost and the Cursed Gold
A 2D arcade-style Java game designed for the CSSE375 term project

## Installation
Before running, make sure Java is installed on your computer. We recommend [OpenJDK 21](https://learn.microsoft.com/en-us/java/openjdk/download#openjdk-21).

1. [Download the latest JAR file from the releases page](https://github.com/rhit-koenigjl/CSSE375-team15/releases/download/v1.0/IndianaGhost.jar)
2. Open the program as normal and start playing!

Note: This build does not include an API key for AI-generated transition messages. Follow the [Google Gemini Integration](#google-gemini-integration) instructions to add your own credentials.

## Building
1. Clone the repository to your computer
2. Open the directory in your Java IDE of choice
3. Use the following commands to build and run the program:
   - `./gradlew build`: run tests and compile source code
   - `./gradlew run`: start the program's GUI
   - `./gradlew clean`: remove the compiled source code
   - `./gradlew shadowJar`: generate a runnable JAR file (outputs to `build/libs`)

### Level Editor
The project repository includes a Python level creator. To use:
1. Navigate to the `level_creator` directory
2. Run `python level_creator_app.py`

### Google Gemini Integration
To generate your personal API key:
1. Visit [Google AI Studio](https://aistudio.google.com)
2. Click the Get API key button
3. Click Create API key and select a Google Cloud project (or create a new one)
4. Copy the generated key and save it for later

To register the key with the program:
1. Navigate to the `src/main/resources` directory
2. Create a new file called `apiKey.local` (this file is not tracked by Git)
3. Paste your API key into the file and save

To change the generated messages:
1. Navigate to the `src/main/resources` directory
2. Open the `apiPrompt.txt` file
3. Edit the contents and save
