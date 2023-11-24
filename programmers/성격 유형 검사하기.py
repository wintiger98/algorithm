def solution(survey, choices):
    feature_mapping = {1: "RT", 2: "CF", 3: "JM", 4: "AN"}
    char_dict = {"R": 0, "T": 0, "C": 0, "F": 0, "J": 0, "M": 0, "A": 0, "N": 0}
    for sur, choice in zip(survey, choices):
        a = sur[0]
        b = sur[1]
        if choice > 4:
            char_dict[b] += choice - 4
        else:
            char_dict[a] += 4 - choice
    answer = ""
    if char_dict["R"] >= char_dict["T"]:
        answer += "R"
    else:
        answer += "T"
    if char_dict["C"] >= char_dict["F"]:
        answer += "C"
    else:
        answer += "F"
    if char_dict["J"] >= char_dict["M"]:
        answer += "J"
    else:
        answer += "M"
    if char_dict["A"] >= char_dict["N"]:
        answer += "A"
    else:
        answer += "N"
    return answer
