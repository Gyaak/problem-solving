def solution(queue1, queue2):
    sum1 = sum(queue1)
    sum2 = sum(queue2)
    total = queue1 + queue2
    total_len = len(total)
    
    if (sum1 + sum2) % 2:
        return -1

    goal = (sum1 + sum2) / 2
    left = 0
    right = int(total_len / 2)
    count = 0
    
    s = set()
    while True:
        if (left, right) in s:
            return -1
        s.add((left, right))
        if sum1 == goal:
            return count
        
        if sum1 < goal:
            sum1 += total[right]
            right = (right + 1) % total_len
        else:
            sum1 -= total[left]
            left = (left + 1) % total_len

        count += 1

    return -1