def solution(numbers):
    answer = [-1 for _ in range(len(numbers))]
    stack = []
    for i, number in enumerate(numbers):
        while stack and stack[-1][1] < number:
            answer[stack[-1][0]] = number
            stack.pop()
        stack.append((i, number))
    return answer