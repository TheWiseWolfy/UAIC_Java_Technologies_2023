import requests
import webbrowser

# Define the base URL of your servlet
base_url = "http://localhost:8080/HelloWorld-1.0-SNAPSHOT/GraphGenerator"

# Define the query parameters
query_parameters = {
    'numVertices': 4  # You can adjust the number of vertices as needed
}

try:
    response = requests.get(base_url, params=query_parameters)

    if response.status_code == 200:
        adjacency_matrix = response.text  # The response text contains the adjacency matrix
        print(f"Adjacency Matrix:\n{adjacency_matrix}")
        with open("output.html", "w") as f:
            f.write(adjacency_matrix)
        webbrowser.open("output.html")

    else:
        print(f"Failed to retrieve data from the servlet. Response code: {response.status_code}")

except requests.exceptions.RequestException as e:
    print(f"An error occurred: {e}")