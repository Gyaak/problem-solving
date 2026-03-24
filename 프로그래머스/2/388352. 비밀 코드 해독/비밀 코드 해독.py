def count_bit(x):
    count = 0
    while x:
        x &= x - 1
        count += 1
    return count

def iterate_combinations(n, k):
    def gospers_hack(x):
        c = x & -x
        r = x + c
        return (((r ^ x) >> 2) // c) | r

    x = (1 << k) - 1
    limit = 1 << n
    
    while x < limit:
        yield x
        x = gospers_hack(x)

def list2binary(ll:list)->int:
    result = 0
    for l in ll:
        result += 1 << (l - 1)
    return result

def solution(n, q, ans):
    answer = 0
    masks = [list2binary(mask) for mask in q]
    
    for num in iterate_combinations(n, 5):
        flag = True
        for a, m in zip(ans,masks):
            if a != count_bit(num&m):
                flag = False
                break
        answer += 1 if flag else 0

    return answer
