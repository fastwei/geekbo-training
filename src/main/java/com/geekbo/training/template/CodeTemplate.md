# 常用的LeetCode算法解题模板：

- 这些是常用的LeetCode算法解题模板，根据不同题目的要求，选择合适的模板进行解题。
- 使用模板可以帮助我们更好地组织代码逻辑，提高解题的效率。

## 递归模板：

定义递归函数，指定输入参数和返回值类型。
编写递归终止条件，即满足某个条件时结束递归，返回终止条件下的结果。
处理当前层逻辑。
递归调用，进入下一层。
处理当前层的结果。
返回当前层的结果。

## 动态规划模板：

定义状态数组或变量，用于存储中间状态或最优解。
初始化状态数组或变量。
确定状态转移方程，即当前状态如何从之前的状态转移得到。
使用循环遍历求解状态数组或变量。
返回最终的结果。

## 双指针模板：

初始化左右指针，通常指向数组的首尾元素。
使用循环遍历，移动左右指针，根据题目要求进行操作。
返回最终的结果。

## 深度优先搜索（DFS）模板：

定义DFS函数，指定输入参数和返回值类型。
编写DFS终止条件，即满足某个条件时结束DFS，返回终止条件下的结果。
处理当前层逻辑。
遍历当前层所有可能的下一步选择。
递归调用DFS函数，进入下一层。
处理当前层的结果。

## 广度优先搜索（BFS）模板：

初始化队列，用于存储待遍历的节点。
将起始节点加入队列。
使用循环遍历队列，将队列中的节点依次出队，并处理当前层逻辑。
将当前节点的邻居节点加入队列。
处理当前层的结果。

## 回溯模板：

定义回溯函数，指定输入参数和返回值类型。
编写回溯终止条件，即满足某个条件时结束回溯，返回终止条件下的结果。
遍历所有可能的选择。
做出选择，更新状态。
递归调用回溯函数，进入下一层。
撤销选择，恢复状态。

## 排序模板：

使用合适的排序算法（如快速排序、归并排序等）对数组或链表进行排序。
返回排序后的结果。

## 栈模板：

初始化一个栈，用于存储元素。
使用循环遍历，根据题目要求进行入栈、出栈、或其他操作。
返回最终的结果。

## 哈希表模板：

初始化一个哈希表，用于存储键值对。
使用循环遍历，根据题目要求进行元素的插入、查询、删除等操作。
返回最终的结果。

## 贪心算法模板：

初始化结果变量。
根据题目要求，选择合适的贪心策略。
迭代地进行贪心选择，更新结果变量。
返回最终的结果。

## 搜索模板：

初始化搜索队列或堆栈，用于存储待搜索的节点或状态。
将起始节点或初始状态加入搜索队列或堆栈。
使用循环遍历搜索队列或堆栈，取出节点或状态，并进行相应的处理。
根据题目要求，将新的节点或状态加入搜索队列或堆栈。
返回最终的结果。

## 链表模板：

定义链表节点的数据结构。
初始化头节点和指针。
使用循环遍历链表，处理节点的值或指针。
返回最终的结果。

## 树模板：

定义树节点的数据结构。
初始化根节点和指针。
使用递归或循环遍历树，处理节点的值或指针。
返回最终的结果。

## 图模板：

定义图节点的数据结构。
初始化图的数据结构（如邻接矩阵、邻接表等）。
使用广度优先搜索（BFS）或深度优先搜索（DFS）等算法遍历图，处理节点的值或指针。
返回最终的结果。

## 并查集模板：

初始化并查集，每个元素独立成集合。
使用合并和查找操作，合并集合和查找集合的根节点。
根据题目要求，进行相应的操作（如合并集合、判断元素是否属于同一集合等）。
返回最终的结果。