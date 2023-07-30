# Project 4 - Lewis Structures 

### Make a circle chart (Lewis Structure) similar to a chemical compound chart, that displays circles with letters. Each atom is a node on the chart and can be connected in 4 different directions to another node. You are to modify the DoublyLinkedList class to accommodate nodes with 4 pointers. You can also use the position class to maintain your location in the data structure. The actual value of the position (as before) can be the node's OO addresss. The circles, representing nocdes, are attached in directions: North,East,South, West.  The attached ppt shows each method and its effect on the compound. 

### Your methods are:
### 1)  Root(letter)  - establish the root with the letter, set position to the root The letter would be the value of the node.
### 2)  Add (letter,direction)  - Add this letter to the compound at the current position , using the direction.
### 3)  Move(direction) - Move to another with out adding, that is just change the position.
### 4) Print - print all letters, in any order. You must traverse the tree to make your list. Do not keep a separate node list in your class. You can traverse the tree recursively. Assume no cycles - An example of a cycle would be if A connected to K in the attached chart examples.
### 5)  Extra credit (3 points)  if you can actually show the letters in a Lewis Structure format with lines between the elements instead of  arrows, and not the  circles you may see on the attached chart.